package org.vmlurui.remclogin.listeners;

import org.vmlurui.remclogin.LoginMod;
import org.vmlurui.remclogin.PlayerLogin;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnGameMessage {
    public static boolean canSendMessage(ServerPlayNetworkHandler networkHandler, ChatMessageC2SPacket packet) {
        ServerPlayerEntity player = networkHandler.player;
        PlayerLogin playerLogin = LoginMod.getPlayer(player);
        String message = packet.getChatMessage();
        // TODO: config to allow more commands when you're not logged
        // 如下填入的指令会在未登陆的时候允许运行
        if (!playerLogin.isLoggedIn() && (message.startsWith("/l") || message.startsWith("/reg"))) {
            return true;
        }
        return playerLogin.isLoggedIn();
    }
}
