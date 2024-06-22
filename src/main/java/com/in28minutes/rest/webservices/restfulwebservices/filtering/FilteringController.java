package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    public static final String SOME_BEAN_FILTER = "SomeBeanFilter";

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        String[] strings = {"field1", "field3"};

        return getMappingJacksonValue(someBean, strings);
    }

    private static MappingJacksonValue getMappingJacksonValue(Object value, String[] strings) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(value);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(strings);
        FilterProvider filters = new SimpleFilterProvider().addFilter(SOME_BEAN_FILTER, filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
        String[] strings = {"field2", "field3"};

        return getMappingJacksonValue(someBeans, strings);
    }
}