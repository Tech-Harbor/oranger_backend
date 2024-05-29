package com.example.backend.web.Advertisement;

import com.example.backend.utils.general.Helpers;
import com.example.backend.web.Advertisement.store.AdvertisementEntity;
import com.example.backend.web.Advertisement.store.dto.AdvertisementCreateDTO;
import com.example.backend.web.Advertisement.store.dto.AdvertisementDTO;
import com.example.backend.web.Advertisement.store.factory.AdvertisementCreateFactory;
import com.example.backend.web.Advertisement.store.factory.AdvertisementFactory;
import com.example.backend.web.Category.CategoryService;
import com.example.backend.web.User.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementCreateFactory advertisementCreateFactory;
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementFactory advertisementFactory;
    private final CategoryService categoryService;
    private final UserService userService;
    private final Helpers helpers;

    @Override
    @Transactional
    public AdvertisementCreateDTO createAdvertisement(final String jwt, final AdvertisementCreateDTO advertisement) {
        final var user = helpers.tokenUserData(jwt);
        final var userName = userService.getByUserFirstName(user.getFirstname());
        final var categoryName = categoryService.getCategoryName(advertisement.category());

        final var newAdvertisement = AdvertisementEntity.builder()
                .user(userName)
                .name(advertisement.name())
                .descriptionAdvertisement(advertisement.descriptionAdvertisement())
                .price(advertisement.price())
                .images(advertisement.images())
                .createDate(LocalDateTime.now())
                .category(categoryName)
                .delivery(advertisement.delivery())
                .build();

        return advertisementCreateFactory.apply(advertisementRepository.save(newAdvertisement));
    }

    @Override
    public List<AdvertisementDTO> getAllAdvertisement() {
        return advertisementRepository.findAll().stream()
                .map(advertisementFactory)
                .collect(Collectors.toList());
    }

    @Override
    public AdvertisementDTO advertisement(final String jwt) {
        final var user = helpers.tokenUserData(jwt);
        final var advertisement =
                advertisementRepository.getByName(user.getAdvertisements().get(0).getName());

        return advertisementFactory.apply(advertisement);
    }

    @Override
    @Transactional
    public AdvertisementDTO editAdvertisement(final String jwt, final AdvertisementDTO advertisementDTO) {
        final var user = helpers.tokenUserData(jwt);
        final var idAdvertisement =
                advertisementRepository.getReferenceById(user.getAdvertisements().get(0).getId());

        if (StringUtils.isNoneEmpty(advertisementDTO.name())) {
            idAdvertisement.setName(advertisementDTO.name());
        }

        if (StringUtils.isNoneEmpty(advertisementDTO.characteristicAdvertisement())) {
            idAdvertisement.setCharacteristicAdvertisement(advertisementDTO.characteristicAdvertisement());
        }

        if (StringUtils.isNoneEmpty(advertisementDTO.descriptionAdvertisement())) {
            idAdvertisement.setDescriptionAdvertisement(advertisementDTO.descriptionAdvertisement());
        }

        if (StringUtils.isNoneEmpty(advertisementDTO.delivery())) {
            idAdvertisement.setDelivery(advertisementDTO.delivery());
        }

        if (StringUtils.isNoneEmpty(advertisementDTO.price().toString())) {
            idAdvertisement.setPrice(advertisementDTO.price());
        }

        return advertisementFactory.apply(advertisementRepository.save(idAdvertisement));
    }

    @Override
    @Transactional
    public void deleteAdvertisement(final String jwt) {
        final var user = helpers.tokenUserData(jwt);
        final var idAdvertisement =
                advertisementRepository.getByName(user.getAdvertisements().get(0).getName());

        advertisementRepository.delete(idAdvertisement);
    }

    @Override
    @Transactional
    public void deleteAll() {
        advertisementRepository.deleteAll();
    }
}