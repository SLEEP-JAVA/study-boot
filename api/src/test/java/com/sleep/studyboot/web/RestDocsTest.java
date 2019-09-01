package com.sleep.studyboot.web;

import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public abstract class RestDocsTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private RestDocumentationContextProvider documentation;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider documentataion) {
        this.documentation = documentataion;
    }

    protected MockMvcRequestSpecification givenMockMvc() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(documentation))
                .build();

        return given().mockMvc(mockMvc);
    }
}