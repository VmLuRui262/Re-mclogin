package org.molaei.mclogin.listeners;

import org.molaei.mclogin.LoginMod;
import org.molaei.mclogin.PlayerLogin;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnPlayerLeave {
    public static void listen(ServerPlayerEntity player) {
        PlayerLogin playerLogin = LoginMod.getPlayer(player);
        playerLogin.setLoggedIn(false);
    }
}
