package pl.aternosmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

public class AternosModClient implements ClientModInitializer {

    private static final String URL = "https://aternos.org/go/";

    @Override
    public void onInitializeClient() {
        ScreenEvents.AFTER_INIT.register((client, screen, width, height) -> {
            if (screen instanceof TitleScreen) {
                Button button = Button.builder(Component.literal("Aternos"), btn -> openAternos())
                        .bounds(5, 5, 80, 20)
                        .build();
                Screens.getWidgets(screen).add(button);
            }
        });
    }

    private static void openAternos() {
        try {
            // Najpierw probujemy Opery
            Process p = new ProcessBuilder("cmd", "/c", "start", "opera", URL).start();
            if (p.waitFor() != 0) {
                // Jesli Opery nie ma, otwieramy domyslna przegladarke
                new ProcessBuilder("cmd", "/c", "start", "", URL).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
