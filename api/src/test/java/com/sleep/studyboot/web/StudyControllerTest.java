package com.sleep.studyboot.web;

import com.sleep.studyboot.core.study.StudyFixture;
import com.sleep.studyboot.core.study.StudyService;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class StudyControllerTest extends RestDocsTest {
    private static final String BASE_URL = "/v1/studies";

    @MockBean
    private StudyService studyService;

    @Test
    public void showStudies() {
        // given
        when(studyService.getAllStudies())
                .thenReturn(Arrays.asList(StudyFixture.aStudyDto()));

        // when
        MockMvcResponse response = givenMockMvc()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when()
                .get(BASE_URL);

        response.prettyPrint();

        // then
        response.then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .apply(
                        document("study-list",
                                preprocessRequest(),
                                preprocessResponse(),
                                responseFields(
                                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("아이디"),
                                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("[].category").type(JsonFieldType.STRING).description("카테고리"),
                                        fieldWithPath("[].description").type(JsonFieldType.STRING).description("설명"),
                                        fieldWithPath("[].place").type(JsonFieldType.STRING).description("장소"),
                                        fieldWithPath("[].capacity").type(JsonFieldType.NUMBER).description("수용 인원"),
                                        fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("시작 날짜"),
                                        fieldWithPath("[].endDate").type(JsonFieldType.STRING).description("종료 날짜"),
                                        fieldWithPath("[].properties[].name").type(JsonFieldType.STRING).description("속성 이름"),
                                        fieldWithPath("[].properties[].value").type(JsonFieldType.STRING).description("속성 값"),
                                        fieldWithPath("[].status").type(JsonFieldType.STRING).description("상태")
                                )
                        ));
    }
}