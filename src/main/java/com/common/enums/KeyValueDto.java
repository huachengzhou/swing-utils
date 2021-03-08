package com.common.enums;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangpc on 2017/8/17.
 */
public class KeyValueDto implements Serializable {
    private String key;
    private String value;
    private String explain;
    private KeyValueDto keyValueDto;
    private List<KeyValueDto> keyValueDtos;

    public KeyValueDto() {
    }

    public KeyValueDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueDto(String key, String value, String explain, KeyValueDto keyValueDto, List<KeyValueDto> keyValueDtos) {
        this.key = key;
        this.value = value;
        this.explain = explain;
        this.keyValueDto = keyValueDto;
        this.keyValueDtos = keyValueDtos;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExplain() {
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public KeyValueDto getKeyValueDto() {
        return this.keyValueDto;
    }

    public void setKeyValueDto(KeyValueDto keyValueDto) {
        this.keyValueDto = keyValueDto;
    }

    public List<KeyValueDto> getKeyValueDtos() {
        return this.keyValueDtos;
    }

    public void setKeyValueDtos(List<KeyValueDto> keyValueDtos) {
        this.keyValueDtos = keyValueDtos;
    }
}
