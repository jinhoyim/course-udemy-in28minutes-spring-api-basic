package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class VersioningPersonController {
    
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }
    
    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

        @GetMapping(value = "/person", params = "version=1")
        public PersonV1 getPersonWithQueryParamV1() {
            return new PersonV1("Bob Charlie");
        }

        @GetMapping(value = "/person", params = "version=2")
        public PersonV2 getPersonWithQueryParamV2() {
            return new PersonV2(new Name("Bob", "Charlie"));
        }

        @GetMapping(value = "/person/header", headers = "x-api-version=1")
        public PersonV1 getPersonWithHeaderV1() {
            return new PersonV1("Bob Charlie");
        }

        @GetMapping(value = "/person/header", headers = "x-api-version=2")
        public PersonV2 getPersonWithHeaderV2() {
            return new PersonV2(new Name("Bob", "Charlie"));
        }

        @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
        public PersonV1 getPersonWithAcceptHeaderV1() {
            return new PersonV1("Bob Charlie");
        }
}
