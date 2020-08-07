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

package fiftyone.geolocation.data;

import fiftyone.geolocation.core.data.GeoDataDefault;
import fiftyone.pipeline.core.data.FlowData;
import fiftyone.pipeline.core.data.TryGetResult;
import fiftyone.pipeline.core.data.types.JavaScript;
import fiftyone.pipeline.engines.data.AspectPropertyValue;
import fiftyone.pipeline.engines.data.AspectPropertyValueDefault;
import fiftyone.pipeline.engines.flowelements.AspectEngine;
import fiftyone.pipeline.engines.services.MissingPropertyService;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;
import org.slf4j.Logger;

public class CloudGeoDataDefault extends GeoDataDefault implements CloudGeoData {

    private Map<String, String> noValueReasons;

    public CloudGeoDataDefault(Logger logger, FlowData flowData, AspectEngine engine, MissingPropertyService missingPropertyService) {
        super(logger, flowData, engine, missingPropertyService);
    }

    public void setNoValueReasons(Map<String, String> value){
        noValueReasons = value;
    }

    @Override
    public AspectPropertyValue<JavaScript> getJavaScript() {
        return getAs("javascript", AspectPropertyValue.class, JavaScript.class);
    }

    @Override
    public AspectPropertyValue<String> getRoad() {
        return getValueAsString("road");
    }

    @Override
    public AspectPropertyValue<String> getTown() {
        return getValueAsString("town");
    }

    @Override
    public AspectPropertyValue<String> getSuburb() {
        return getValueAsString("suburb");
    }

    @Override
    public AspectPropertyValue<String> getCounty() {
        return getValueAsString("county");
    }

    @Override
    public AspectPropertyValue<String> getRegion() {
        return getValueAsString("region");
    }

    @Override
    public AspectPropertyValue<String> getState() {
        return getValueAsString("state");
    }

    @Override
    public AspectPropertyValue<String> getZipCode() {
        return getValueAsString("zipcode");
    }

    @Override
    public AspectPropertyValue<String> getCountry() {
        return getValueAsString("country");
    }

    @Override
    public AspectPropertyValue<String> getCountryCode() {
        return getValueAsString("countrycode");
    }

    @Override
    public AspectPropertyValue<String> getAddress() {
        return getValueAsString("address");
    }

    private AspectPropertyValue<String> getValueAsString(String key) {
        AspectPropertyValue<String> value = getAs(key, AspectPropertyValue.class);

        if(noValueReasons.containsKey(key))
            value.setNoValueMessage(noValueReasons.get(key));

        return value;
    }

    @Override
    protected <T> TryGetResult<T> tryGetValue(String key, Class<T> type, Class<?>... parameterisedTypes) {
        if (AspectPropertyValue.class.isAssignableFrom(type)) {
            TryGetResult<T> result = new TryGetResult<>();
            Map<String, Object> map = asKeyMap();
            if (map.containsKey(key)) {
                Object obj = map.get(key);

                try {
                    Object temp = setValue(key, obj);
                    T value = type.cast(temp);
                    result.setValue(value);
                } catch (ClassCastException e) {
                    throw new ClassCastException("Expected property '" + key +
                        "' to be of type '" + type.getSimpleName() +
                        "' but it is '" + obj.getClass().getSimpleName() + "'");
                }
            }
            return result;
        }
        else {
            return super.tryGetValue(key, type, parameterisedTypes);
        }
    }

    private AspectPropertyValue setValue(String key, Object obj) {
        AspectPropertyValue temp = new AspectPropertyValueDefault();
        if (Objects.isNull(obj) || obj == JSONObject.NULL) {
            temp.setNoValueMessage(noValueReasons.get(key));
        }
        else {
            temp.setValue(obj);
        }
        return temp;
    }

}
