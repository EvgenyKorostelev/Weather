package ru.korostelev.Weather.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReceiveWeatherUserPayload(

        @NotNull(message = "{weather.errors.user.name_size_is_null}")
        @Size(min = 3, max = 10, message = "{weather.errors.user.name_size_is_invalid}")
        String userName
) {
}
