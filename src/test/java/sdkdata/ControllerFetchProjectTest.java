package sdkdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerFetchProjectTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFetchProject() throws Exception {
        this.mockMvc.perform(get("/sdkguide/analyze"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
