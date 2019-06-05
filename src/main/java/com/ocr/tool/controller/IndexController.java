package com.ocr.tool.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by truongnhukhang on 6/5/19.
 */
@RequestMapping
public class IndexController {

  @RequestMapping(value = "/")
  public String index() {
    return "index";
  }
}
