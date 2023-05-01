package com.cnu.real_coding_server.service.week1.practice.service.fixture;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.entity.Post.PostBuilder;
import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.model.type.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;

public class PostFixture {

    static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    public static PostRequest getSlangPostRequest() {
        String title = "비속어1";
        String content = "비속어2";
        Tag tag = Tag.SPRINGBOOT;

        try {
            return mapper.readValue(
                    """
                            {
                                "title": "%s",
                                "contents": "%s",
                                "tag": "%s"
                            }
                            """.formatted(title, content, tag)
                    , PostRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static PostRequest getNormalPostRequest() {
        String title = "정상 제목";
        String content = "정상 본문";
        Tag tag = Tag.SPRINGBOOT;

        try {
            return mapper.readValue(
                    """
                            {
                                "title": "%s",
                                "contents": "%s",
                                "tag": "%s"
                            }
                            """.formatted(title, content, tag)
                    , PostRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static PostRequest getNormalPostRequestUpdated() {
        String title = "정상 제목2";
        String content = "정상 본문2";
        Tag tag = Tag.JAVA;

        try {
            return mapper.readValue(
                    """
                            {
                                "title": "%s",
                                "contents": "%s",
                                "tag": "%s"
                            }
                            """.formatted(title, content, tag)
                    , PostRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Post getNormalPost() {
        String title = "정상 제목2";
        String content = "정상 본문2";
        Tag tag = Tag.JAVA;

        try {
            return mapper.readValue(
                    """
                            {
                                "id": 1,
                                "title": "%s",
                                "contents": "%s",
                                "tag": "%s",
                                "createdAt": "2023-01-16T11:50:19.917",
                                "updatedAt": "2023-05-01T11:50:19.917"
                            }
                            """.formatted(title, content, tag)
                    , Post.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}