package com.cnu.real_coding_server.service.week2.base.controller;


import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cnu.real_coding_server.model.type.Tag;
import com.cnu.real_coding_server.service.PostService;
import com.cnu.real_coding_server.service.week1.practice.service.fixture.PostFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.constant.ClassDesc;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// controller 를 테스트
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@AutoConfigureMockMvc // -> webAppContextSetup(webApplicationContext)
@AutoConfigureRestDocs // -> apply(documentationConfiguration(restDocumentation))
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @BeforeEach
//    public void setUp(WebApplicationContext webApplicationContext,
//                      RestDocumentationContextProvider restDocumentation) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentation))
//                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())))
//                .build();
//    }

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean // 2
    private PostService postService;

    @Test
    void getPosts() throws Exception {
        // given
        given(postService.getPosts())
                .willReturn(List.of(PostFixture.getNormalPost())); // 3

        // when & then
        mockMvc.perform(get("/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        responseFields(
                                List.of(
                                        fieldWithPath("[]").type(JsonFieldType.ARRAY)
                                                .description("결과 데이터"),
                                        fieldWithPath("[].title").type(JsonFieldType.STRING)
                                                .description("제목"),
                                        fieldWithPath("[].contents").type(JsonFieldType.STRING)
                                                .description("글 본문"),
                                        fieldWithPath("[].tag").type(JsonFieldType.STRING)
                                                .description("태그"),
                                        fieldWithPath("[].id").type(JsonFieldType.NUMBER)
                                                .description("id"),
                                        fieldWithPath("[].createdAt").type(JsonFieldType.STRING)
                                                .description("createdAt"),
                                        fieldWithPath("[].updatedAt").type(JsonFieldType.STRING)
                                                .description("updatedAt")
                                )
                        )
                ));
    }
}
