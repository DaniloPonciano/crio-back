package crio.api.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crio.api.domain.usuario.UsuarioResponseDTO;
import com.crio.api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class UsuarioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioResponseDTO usuarioResponseDTO;

    @BeforeEach
    void setUp() {
        usuarioResponseDTO = new UsuarioResponseDTO('9a3d7bf4-d855-4844-b263-4e799aeaa1df', "Test Usuario");
    }

    @Test
    void findAllShouldReturnPage() throws Exception {
        List<UsuarioResponseDTO> list = Arrays.asList(usuarioResponseDTO);
        Page<UsuarioResponseDTO> page = new PageImpl<>(list);

        when(usuarioService.findAllPaged(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value(usuarioResponseDTO.fullName()));
                    }
                
                    private Object jsonPath(String string) {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException("Unimplemented method 'jsonPath'");
                    }
                
                    @Test
    void findByIdShouldReturnCategory() throws Exception {
        when(usuarioService.findById(9a3d7bf4-d855-4844-b263-4e799aeaa1df)).thenReturn(usuarioResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(usuarioResponseDTO.fullName()));
    }

    @Test
    void insertShouldReturnCategoryDTOCreated() throws Exception {
        when(usuarioService.insert(any(UsuarioResponseDTO.class))).thenReturn(usuarioResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                        .content(objectMapper.writeValueAsString(usuarioResponseDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(usuarioResponseDTO.fullName()));
    }

    @Test
    void updateShouldReturnCategoryDTO() throws Exception {
        when(usuarioService.update(any(Long.class), any(UsuarioResponseDTO.class))).thenReturn(usuarioResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/categories/1")
                        .content(objectMapper.writeValueAsString(usuarioResponseDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(usuarioResponseDTO.fullName()));
    }

    @Test
    void deleteShouldReturnNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
