package org.vmlurui.remclogin.commands;

import org.vmlurui.remclogin.LoginMod;
import org.vmlurui.remclogin.PlayerLogin;
import org.vmlurui.remclogin.RegisteredPlayersJson;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

public class RegisterCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("reg")
                .then(argument("newPassword", StringArgumentType.word())
                    .then(argument("confirmPassword", StringArgumentType.word())
                        .executes(ctx -> {
                            String password = StringArgumentType.getString(ctx, "newPassword");
                            ServerPlayerEntity player = ctx.getSource().getPlayer();
                            String username = player.getEntityName();
                            if (RegisteredPlayersJson.isPlayerRegistered(username)) {//判断是否存在此用户名 若存在 提示如下
                                ctx.getSource().sendFeedback(new LiteralText("§cYou're already registered! Use /l instead."), false);
                                ctx.getSource().sendFeedback(new LiteralText("§c您已经注册!请使用 /l 进行登录。"), false);
                                return 1;
                            }
                            if (!password.equals(StringArgumentType.getString(ctx, "confirmPassword"))) {//判断密码与确认密码不一致
                                ctx.getSource().sendFeedback(new LiteralText("§cPasswords don't match! Repeat it correctly."), false);
                                ctx.getSource().sendFeedback(new LiteralText("§c密码不一致!请重试。"), false);
                                return 1;
                            }
                            //若允许注册，运行如下
                            String uuid = ctx.getSource().getPlayer().getUuidAsString();
                            RegisteredPlayersJson.save(uuid, username, password);
                            PlayerLogin playerLogin = LoginMod.getPlayer(ctx.getSource().getPlayer());
                            playerLogin.setLoggedIn(true);
                            player.setInvulnerable(false);
                            ctx.getSource().sendFeedback(new LiteralText("§aSuccessfully registered."), false);
                            ctx.getSource().sendFeedback(new LiteralText("§a注册成功"), false);
                            return 1;
        }))));
    }
}
