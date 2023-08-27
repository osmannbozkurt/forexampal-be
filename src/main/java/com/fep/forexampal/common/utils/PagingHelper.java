package com.fep.forexampal.common.utils;

import com.fep.forexampal.common.model.PagingModel;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

@UtilityClass
public class PagingHelper {

    public static <T> PagingModel build(Page<T> page) {
        return PagingModel.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .total(page.getTotalElements())
                .build();
    }
}
