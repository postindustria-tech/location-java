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

package fiftyone.geolocation.core.data;

import fiftyone.pipeline.core.data.FlowData;
import fiftyone.pipeline.core.data.types.JavaScript;
import fiftyone.pipeline.engines.data.AspectDataBase;
import fiftyone.pipeline.engines.data.AspectPropertyValue;
import fiftyone.pipeline.engines.data.AspectPropertyValueDefault;
import fiftyone.pipeline.engines.flowelements.AspectEngine;
import fiftyone.pipeline.engines.services.MissingPropertyService;
import org.slf4j.Logger;

public class GeoDataDefault extends AspectDataBase implements GeoData {

    public GeoDataDefault(Logger logger, FlowData flowData, AspectEngine engine, MissingPropertyService missingPropertyService) {
        super(logger, flowData, engine, missingPropertyService);
        setJavaScript(new AspectPropertyValueDefault<JavaScript>(new JavaScript("")));
    }


    @Override
    public AspectPropertyValue<JavaScript> getJavaScript() {
        return getAs("javascript", AspectPropertyValue.class, JavaScript.class);
    }

    @Override
    public void setJavaScript(AspectPropertyValue<JavaScript> javaScript) {
        put("javascript", javaScript);
    }

    @Override
    public AspectPropertyValue<String> getRoad() {
        return getAs("road", AspectPropertyValue.class);
    }

    @Override
    public void setRoad(AspectPropertyValue<String> road) {
        put("road", road);
    }

    @Override
    public AspectPropertyValue<String> getTown() {
        return getAs("town", AspectPropertyValue.class);
    }

    @Override
    public void setTown(AspectPropertyValue<String> town) {
        put("town", town);
    }

    @Override
    public AspectPropertyValue<String> getSuburb() {
        return getAs("suburb", AspectPropertyValue.class);
    }

    @Override
    public void setSuburb(AspectPropertyValue<String> suburb) {
        put("suburb", suburb);
    }

    @Override
    public AspectPropertyValue<String> getCounty() {
        return getAs("county", AspectPropertyValue.class);
    }

    @Override
    public void setCounty(AspectPropertyValue<String> county) {
        put("county", county);
    }

    @Override
    public AspectPropertyValue<String> getRegion() {
        return getAs("region", AspectPropertyValue.class);
    }

    @Override
    public void setRegion(AspectPropertyValue<String> region) {
        put("region", region);
    }

    @Override
    public AspectPropertyValue<String> getState() {
        return getAs("state", AspectPropertyValue.class);
    }

    @Override
    public void setState(AspectPropertyValue<String> state) {
        put("state", state);
    }

    @Override
    public AspectPropertyValue<String> getZipCode() {
        return getAs("zipcode", AspectPropertyValue.class);
    }

    @Override
    public void setZipCode(AspectPropertyValue<String> zipCode) {
        put("zipcode", zipCode);
    }

    @Override
    public AspectPropertyValue<String> getCountry() {
        return getAs("country", AspectPropertyValue.class);
    }

    @Override
    public void setCountry(AspectPropertyValue<String> country) {
        put("country", country);
    }

    @Override
    public AspectPropertyValue<String> getCountryCode() {
        return getAs("countrycode", AspectPropertyValue.class);
    }

    @Override
    public void setCountryCode(AspectPropertyValue<String> countryCode) {
        put("countrycode", countryCode);
    }

    @Override
    public AspectPropertyValue<String> getAddress() {
        return getAs("address", AspectPropertyValue.class);
    }

    @Override
    public void setAddress(AspectPropertyValue<String> address) {
        put("address", address);
    }
}
