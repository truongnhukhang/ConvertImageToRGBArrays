package com.ocr.tool.dto;

/**
 * Created by truongnhukhang on 6/5/19.
 */
public class ImageInfoDto {
  String signature_name = "serving_default";
  float[][][][] inputs;

  public String getSignature_name() {

    return signature_name;
  }

  public void setSignature_name(String signature_name) {
    this.signature_name = signature_name;
  }

  public float[][][][] getInputs() {
    return inputs;
  }

  public void setInputs(float[][][][] inputs) {
    this.inputs = inputs;
  }
}
