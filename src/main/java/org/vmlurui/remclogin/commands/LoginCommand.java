package org.vmlurui.remclogin.commands;

import org.vmlurui.remclogin.LoginMod;
import org.vmlurui.remclogin.PlayerLogin;
import org.vmlurui.remclogin.RegisteredPlayersJson;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import net.minecraft.network.packet.s2c.play.PlaySoundIdS2CPacket;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class LoginCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("l")
                .then(argument("password", StringArgumentType.word())
                    .executes(ctx -> {
                        String password = StringArgumentType.getString(ctx, "password");
                        String username = ctx.getSource().getPlayer().getEntityName();
                        ServerPlayerEntity player = ctx.getSource().getPlayer();
                        PlayerLogin playerLogin = LoginMod.getPlayer(ctx.getSource().getPlayer());
                        
                        if (playerLogin.isLoggedIn()) {
                            ctx.getSource().sendFeedback(new LiteralText("§cYou are already logged in! Please don't log in again!"), false);
                            ctx.getSource().sendFeedback(new LiteralText("§c您已经登录了! 请不要再次登录!"), false);
                        }
                        else if (!RegisteredPlayersJson.isPlayerRegistered(username)) {
                            ctx.getSource().sendFeedback(new LiteralText("§cYou're not registered! Use /register instead."), false);
                            ctx.getSource().sendFeedback(new LiteralText("§c你还未在本服务器注册，请使用 /register 进行注册。"), false);
                        } else if (RegisteredPlayersJson.isCorrectPassword(username, password)) {
                            PlayerLogin playerLogin = LoginMod.getPlayer(ctx.getSource().getPlayer());
                            playerLogin.setLoggedIn(true);
                            ctx.getSource().sendFeedback(new LiteralText("§aLogged in."), false);
                            ctx.getSource().sendFeedback(new LiteralText("§a登录成功。"), false);
                            if (!player.isCreative()) {
                                player.setInvulnerable(false);
                            }
                            player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(new Identifier("minecraft:block.note_block.pling"), SoundCategory.MASTER, player.getPos(), 100f, 0f));
                        } else {
                            player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(new Identifier("minecraft:entity.zombie.attack_iron_door"), SoundCategory.MASTER, player.getPos(), 100f, 0.5f));
                            ctx.getSource().sendFeedback(new LiteralText("§cIncorrect password!"), false);
                            ctx.getSource().sendFeedback(new LiteralText("§c密码错误!"), false);
                        }
                        return 1;
        })));
    }
}
