package ru.korostelev.Weather.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserPayload(

        @NotNull(message = "{weather.errors.user.name_size_is_null}")
        @Size(min = 3, max = 10, message = "{weather.errors.user.name_size_is_invalid}")
        String userName,

        @NotNull(message = "{weather.errors.user.key_size_is_null}")
        @Size(max = 500, message = "{weather.errors.user.key_size_is_invalid}")
        String apiKey
) {
}
