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
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class DeRegisterCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("dereg")
        .then(argument("userName", StringArgumentType.word()) //需求:用户名
        .requires(source -> source.hasPermissionLevel(4)) //需求:权限等级4(OP)
        .executes(ctx -> {
            String userName = StringArgumentType.getString(ctx, "userName");
            PlayerLogin playerLogin = LoginMod.getPlayer(ctx.getSource().getPlayer());
            ServerPlayerEntity player = ctx.getSource().getPlayer();

            if (!RegisteredPlayersJson.isPlayerRegistered(userName)) {
                ctx.getSource().sendFeedback(new LiteralText("§cThis userName does not exist or has been deleted!"), false);
                ctx.getSource().sendFeedback(new LiteralText("§c该用户名不存在或已被删除!"), false);
            } else {
                RegisteredPlayersJson.removePlayer(userName);
                player.setInvulnerable(false);
                ctx.getSource().sendFeedback(new LiteralText("§cSuccessfully Deleted  "+ userName + "!"), false);
                ctx.getSource().sendFeedback(new LiteralText("§c已删除用户名 "+ userName + "!"), false);
            }
            return 1;
        })));
    }
}