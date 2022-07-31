package org.vmlurui.remclogin.commands;

import org.vmlurui.remclogin.LoginMod;
import org.vmlurui.remclogin.PlayerLogin;
import org.vmlurui.remclogin.RegisteredPlayersJson;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundIdS2CPacket;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DeRegisterCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("dereg")
        .then(argument("username", StringArgumentType.word()) //需求.用户名
        .requires(source -> source.hasPermissionLevel(4)) //需求.权限等级4(OP)
        .executes(ctx -> {
            String username = StringArgumentType.getString(ctx, "username");//变量 用户名
            PlayerLogin playerLogin = LoginMod.getPlayer(ctx.getSource().getPlayer());
            ServerPlayerEntity player = ctx.getSource().getPlayer();


            if (!RegisteredPlayersJson.isPlayerRegistered(username)) {//判断 账户不存在
                ctx.getSource().sendFeedback(Text.translatable("command.deregister.dereg_failed"), false);
            } else {//否则 视为账户存在 删除开始
                RegisteredPlayersJson.removePlayer(username);
                player.setInvulnerable(false);
                ctx.getSource().sendFeedback(Text.translatable("command.deregister.dereg_success",username), false);
            }
            return 1;
        })));
    }
}