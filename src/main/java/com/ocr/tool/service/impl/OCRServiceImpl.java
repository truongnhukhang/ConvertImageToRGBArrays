package com.ocr.tool.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ocr.tool.dto.ImageInfoDto;
import com.ocr.tool.service.OCRService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by truongnhukhang on 6/5/19.
 */
@Service
public class OCRServiceImpl implements OCRService {

  @Value("${ocr.adult.api}")
  String ocrAdultApi;

  ObjectMapper objectMapper = new ObjectMapper();

  public Map<String, Object> getAdultResult(ImageInfoDto imageInfoDto) {
    String imageInfoJson = null;
    try {
      imageInfoJson = objectMapper.writeValueAsString(imageInfoDto);
      HttpResponse<String> jsonResponse = Unirest.post(ocrAdultApi)
          .header("accept", "application/json")
          .body(imageInfoJson).asString();
      System.out.println(jsonResponse.getBody());
      return objectMapper.readValue(jsonResponse.getBody(),new TypeReference<Map<String, Object>>() {
      });
    } catch (UnirestException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
