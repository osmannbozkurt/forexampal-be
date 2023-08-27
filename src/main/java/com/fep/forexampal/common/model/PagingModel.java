package com.fep.forexampal.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagingModel {

    private int size;
    private int page;
    private long total;

}
