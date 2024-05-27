package com.example.backend.web.Advertisement;

import com.example.backend.utils.annotations.ApiResponseCreated;
import com.example.backend.utils.annotations.ApiResponseDelete;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@Tag(name = "AdvertisementService")
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private static final String URL_CREATE = "/createAdvertisement";
    private static final String URL_EDIT = "/editAdvertisement";
    private static final String URL_DELETE = "/editAdvertisement";
    public static final String ADVERTISEMENT = "/advertisement";
    private static final String URL_DELETE_ALL = "/deleteAll";

    @PostMapping(URL_CREATE)
    @ApiResponseCreated
    public AdvertisementDTO createAdvertisementByUser(@RequestHeader(AUTHORIZATION) final String jwt,
                                                      @RequestBody final AdvertisementDTO entity) {
        return advertisementService.createAdvertisement(jwt, entity);
    }

    @QueryMapping
    public List<AdvertisementDTO> getAllAdvertisement() {
        return advertisementService.getAllAdvertisement();
    }

    @GetMapping(ADVERTISEMENT)
    public AdvertisementDTO getByAdvertisement(@RequestHeader(AUTHORIZATION) final String jwt) {
        return advertisementService.advertisement(jwt);
    }

    @PatchMapping(URL_EDIT)
    public AdvertisementDTO editAdvertisement(@RequestHeader(AUTHORIZATION) final String jwt,
                                              @RequestBody final AdvertisementDTO entity) {
        return advertisementService.editAdvertisement(jwt, entity);
    }

    @DeleteMapping(URL_DELETE)
    @ApiResponseDelete
    public void deleteAdvertisement(@RequestHeader(AUTHORIZATION) final String jwt) {
        advertisementService.deleteAdvertisement(jwt);
    }

    @DeleteMapping(URL_DELETE_ALL)
    @ApiResponseDelete
    public void deleteAllAdvertisement() {
        advertisementService.deleteAll();
    }
}