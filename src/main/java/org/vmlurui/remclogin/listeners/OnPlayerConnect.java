package org.vmlurui.remclogin.listeners;

import org.vmlurui.remclogin.LoginMod;
import org.vmlurui.remclogin.PlayerLogin;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class OnPlayerConnect {
    public static void listen(ServerPlayerEntity player) {
        PlayerLogin playerLogin = LoginMod.getPlayer(player);
        //在如下区域填入进服提示
        //实测中发现优先级比MCDReforged的插件Joinmotd的高
        //会被顶掉
        playerLogin.setLoggedIn(false);
        player.setInvulnerable(true);
        player.sendMessage(Text.translatable("listeners.welcome", player.getEntityName()), false);
        player.networkHandler.sendPacket(new TitleS2CPacket(Text.translatable("listeners.welcome_title")));
    }
}
