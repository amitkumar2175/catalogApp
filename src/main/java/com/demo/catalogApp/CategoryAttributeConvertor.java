package com.demo.catalogApp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;

public class CategoryAttributeConvertor implements AttributeConverter<List<CategoryAttribute>, String> {

    @Override
    public String convertToDatabaseColumn(List<CategoryAttribute> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return null;
        }
        return JsonUtils.serializeObject(attributes);
    }

    @Override
    public List<CategoryAttribute> convertToEntityAttribute(String attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return null;
        }
        CategoryAttribute[] templateAttributes = JsonUtils.deserializeObject(attributes, CategoryAttribute[].class);
        if (templateAttributes != null && templateAttributes.length > 0) {
            return Arrays.asList(templateAttributes);
        }
        return Collections.emptyList();
    }

}
