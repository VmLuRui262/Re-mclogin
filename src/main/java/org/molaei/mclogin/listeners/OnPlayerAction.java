package org.molaei.mclogin.listeners;

import org.molaei.mclogin.LoginMod;
import org.molaei.mclogin.PlayerLogin;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnPlayerAction {
    public static boolean canInteract(ServerPlayNetworkHandler networkHandler) {
        ServerPlayerEntity player = networkHandler.player;
        PlayerLogin playerLogin = LoginMod.getPlayer(player);
        return playerLogin.isLoggedIn();
    }
}
