package pl.edu.pk.cosmo.habsatbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pk.cosmo.habsatbackend.model.DataFrame;
import pl.edu.pk.cosmo.habsatbackend.service.DataFrameService;

import java.util.List;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
@RestController
@RequestMapping("/data-frame")
public class DataFrameController {
    private final DataFrameService dataFrameService;

    public DataFrameController(DataFrameService dataFrameService) {
        this.dataFrameService = dataFrameService;
    }

    @GetMapping
    public List<DataFrame> getAllDataFrames() {
        return dataFrameService.getDataFrames();
    }
}
