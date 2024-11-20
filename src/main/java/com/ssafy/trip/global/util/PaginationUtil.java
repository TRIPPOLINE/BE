package com.ssafy.trip.global.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationUtil {

    public static class PageRequest {
        private int page;
        private int size;
        private int offset;

        public PageRequest(int page, int size) {
            this.page = page;
            this.size = size;
            this.offset = (page - 1) * size;
        }

        // getters
        public int getPage() { return page; }
        public int getSize() { return size; }
        public int getOffset() { return offset; }
    }

    public static <T> PageResult<T> getPageResult(List<T> content, int page, int size, long totalElements) {
        PageResult<T> result = new PageResult<>();
        result.setContent(content);
        result.setCurrentPage(page);
        result.setSize(size);
        result.setTotalElements(totalElements);
        result.setTotalPages((int) Math.ceil((double) totalElements / size));
        return result;
    }

    public static PageRequest createPageRequest(int page, int size) {
        return new PageRequest(page, size);
    }

    public static Map<String, Object> createPaginationMap(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", (page - 1) * size);
        params.put("limit", size);
        return params;
    }
}