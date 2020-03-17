package com.kdu.common.util;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SSLConnectTest {

    @Test
    void 정상적으로_documnet를_가져온다() throws Exception {
        Document document = SSLConnect.getDocument("C08");
        assertThat(document).isNotNull();
    }
}