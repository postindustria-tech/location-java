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

package fiftyone.geolocation.data.tests;

import org.junit.Before;
import org.junit.Test;

import fiftyone.geolocation.Wrapper;
import fiftyone.geolocation.WrapperDigitalElement;

public class DEValueTests  {

    	private Wrapper wrapper;
    	
        @Before
        public void init() throws Exception {
        	wrapper = new WrapperDigitalElement();
        }

        @Test
        public void ValueTests_Cloud_ValueTypes() throws Exception {
            ValueTests.valueTypes(wrapper);
        }

        @Test
        public void ValueTests_Cloud_AvailableProperties() throws Exception {
            ValueTests.availableProperties(wrapper);
        }

        @Test
        public void ValueTests_Cloud_TypedGetters() throws Exception {
            ValueTests.typedGetters(wrapper);
        }
    }
