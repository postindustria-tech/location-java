/* *********************************************************************
 * This Original Work is copyright of 51 Degrees Mobile Experts Limited.
 * Copyright 2019 51 Degrees Mobile Experts Limited, 5 Charlotte Close,
 * Caversham, Reading, Berkshire, United Kingdom RG4 7BY.
 *
 * This Original Work is licensed under the European Union Public Licence (EUPL)
 * v.1.2 and is subject to its terms as set out below.
 *
 * If a copy of the EUPL was not distributed with this file, You can obtain
 * one at https://opensource.org/licenses/EUPL-1.2.
 *
 * The 'Compatible Licences' set out in the Appendix to the EUPL (as may be
 * amended by the European Commission) shall be deemed incompatible for
 * the purposes of the Work and the provisions of the compatibility
 * clause in Article 5 of the EUPL shall not apply.
 *
 * If using the Work as, or as part of, a network application, by
 * including the attribution notice(s) required under Article 5 of the EUPL
 * in the end user terms of the application under an appropriate heading,
 * such notice(s) shall fulfill the requirements of that article.
 * ********************************************************************* */

package fiftyone.geolocation.flowelements;

import fiftyone.geolocation.core.Enums;
import fiftyone.geolocation.core.data.GeoData;
import fiftyone.geolocation.data.CloudGeoData;
import fiftyone.pipeline.cloudrequestengine.data.CloudRequestData;
import fiftyone.pipeline.cloudrequestengine.flowelements.CloudRequestEngine;
import fiftyone.pipeline.core.data.AccessiblePropertyMetaData;
import fiftyone.pipeline.core.data.EvidenceKeyFilter;
import fiftyone.pipeline.core.data.EvidenceKeyFilterWhitelist;
import fiftyone.pipeline.core.data.FlowData;
import fiftyone.pipeline.core.data.factories.ElementDataFactory;
import fiftyone.pipeline.core.data.types.JavaScript;
import fiftyone.pipeline.core.exceptions.PipelineConfigurationException;
import fiftyone.pipeline.engines.data.AspectPropertyMetaData;
import fiftyone.pipeline.engines.data.AspectPropertyMetaDataDefault;
import fiftyone.pipeline.engines.data.AspectPropertyValue;
import fiftyone.pipeline.engines.data.AspectPropertyValueDefault;
import fiftyone.pipeline.engines.flowelements.AspectEngineBase;
import org.json.JSONObject;
import org.slf4j.Logger;

import java.util.*;
import org.json.JSONArray;

public class GeoLocationCloudEngine extends AspectEngineBase<CloudGeoData, AspectPropertyMetaData> {

    private CloudRequestEngine cloudRequestEngine = null;
    private List<AspectPropertyMetaData> properties;
    private String dataSourceTier;
    private String elementDataKey;
    private EvidenceKeyFilter evidenceKeyFilter = new EvidenceKeyFilterWhitelist(Collections.EMPTY_LIST);
    private String dataProviderPrefix;

    @Override
    public List<AspectPropertyMetaData> getProperties() {
        return properties;
    }

    @Override
    public String getDataSourceTier() {
        return dataSourceTier;
    }

    @Override
    public EvidenceKeyFilter getEvidenceKeyFilter() {
        // This engine needs no evidence.
        // It works from the cloud request data.
        return evidenceKeyFilter;
    }

    @Override
    public String getElementDataKey() {
        return elementDataKey;
    }

    public String getDataProviderPrefix() {
        return dataProviderPrefix;
    }

    public GeoLocationCloudEngine(
        Logger logger,
        ElementDataFactory<CloudGeoData> aspectDataFactory,
        CloudRequestEngine engine,
        Enums.GeoLocationProvider provider) {
        super(logger, aspectDataFactory);
        if (engine == null) {
            throw new IllegalArgumentException(
                "The '" + getClass().getName() +
                    "' requires a 'CloudRequestEngine' to be passed in the " +
                    "constructor.");
        }
        switch (provider) {
            case DigitalElement:
                elementDataKey = "location_digitalelement";
                dataProviderPrefix = "DigitalElement";
                break;
            case FiftyOneDegrees:
                elementDataKey = "location";
                dataProviderPrefix = "OSM";
                break;
            default:
                throw new PipelineConfigurationException("provider ' +" +
                    provider + "' not supported by the geolocation cloud engine");
        }
        try {
            cloudRequestEngine = engine;
            loadAspectProperties();
        }
        catch (Exception ex) {
            logger.error("Error creating " + getClass().getName(), ex);
            throw new PipelineConfigurationException(
                "Error creating " + getClass().getName(),
                ex);
        }
    }

    @Override
    protected void processEngine(FlowData data, CloudGeoData aspectData) {
        if (cloudRequestEngine != null) {
            CloudRequestData requestData = data.getFromElement(cloudRequestEngine);
            String json = requestData.getJsonResponse();

            JSONObject jsonObj = new JSONObject(json);
            JSONObject geoObj = jsonObj.getJSONObject(getElementDataKey());

            Map<String, Object> methodData = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

            for (String propertyName : geoObj.keySet()) {

            }


            for (AspectPropertyMetaData property : getProperties()) {
                switch(property.getType().getSimpleName()) {
                    case ("List"):
                        JSONArray jsonArray = geoObj.optJSONArray(property.getName().toLowerCase());
                        methodData.put(
                            property.getName(),
                            jsonArray == null ? null : jsonArray.toList());
                        break;
                    case ("JavaScript"):
                        String javascript = geoObj.optString(property.getName().toLowerCase(), null);
                        methodData.put(
                            property.getName(),
                            javascript == null ? null : new JavaScript(javascript));
                        break;
                    default:
                        methodData.put(
                            property.getName(),
                            geoObj.opt(property.getName().toLowerCase()));
                            break;
                }
            }

            aspectData.populateFromMap(methodData);

            Map<String, String> noValueReasonsMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            for(Map.Entry<String, Object> entry : methodData.entrySet()){
                if("null".equals(String.valueOf(entry.getValue()))){
                    String key = this.getElementDataKey() + "." + entry.getKey().toLowerCase();
                    Object reason = tryToGet(geoObj, key);
                    noValueReasonsMap.put(entry.getKey().toLowerCase(), (reason == null ? "" : reason.toString()));
                }
            }

            aspectData.setNoValueReasons(noValueReasonsMap);
        }
        else {
            throw new PipelineConfigurationException(
                "The '" + getClass().getName() + "' requires a 'CloudRequestEngine' " +
                "before it in the Pipeline. This engine will be unable " +
                "to produce results until this is corrected.");
        }
    }

    public static Object tryToGet(JSONObject jsonObj, String key) {
        if (jsonObj.has(key + "nullreason"))
            return jsonObj.opt(key);
        return null;
    }

    @Override
    protected void unmanagedResourcesCleanup() {
    }

    private void loadAspectProperties() throws Exception {
        Map<String, AccessiblePropertyMetaData.ProductMetaData> map =
            cloudRequestEngine.getPublicProperties();

        if (map != null &&
            map.size() > 0 &&
            map.containsKey(getElementDataKey())) {
            properties = new ArrayList<>();
            dataSourceTier = map.get(getElementDataKey()).dataTier;

            for (AccessiblePropertyMetaData.PropertyMetaData item : map.get(getElementDataKey()).properties)
            {
                AspectPropertyMetaData property = new AspectPropertyMetaDataDefault(
                    item.name,
                    this,
                    item.category,
                    item.getPropertyType(),
                    new ArrayList<String>(),
                    true);
                properties.add(property);
            }
        }
        else {
            String reason = "";
            if (map == null ||
                map.size() == 0) {
                reason = "the supplied CloudRequestEngine was unable " +
                    "to determine the properties the cloud service can " +
                    "populate";
            }
            else {
                reason = "the supplied CloudRequestEngine cannot " +
                "populate properties for aspect '" + getElementDataKey() + "'";
            }
            throw new Exception(
                getClass().getName() + " is unable to determine the " +
                "properties it can populate because " + reason + ". " +
                "This will prevent the pipeline from functioning.");
        }
    }
}
