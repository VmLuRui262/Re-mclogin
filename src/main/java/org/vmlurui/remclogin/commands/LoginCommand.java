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
import net.minecraft.text.Text;

public class LoginCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("l")
                .then(argument("password", StringArgumentType.word())
                    .executes(ctx -> {
                        String password = StringArgumentType.getString(ctx, "password");
                        String username = ctx.getSource().getPlayer().getEntityName();
                        ServerPlayerEntity player = ctx.getSource().getPlayer();
                        PlayerLogin playerLogin = LoginMod.getPlayer(ctx.getSource().getPlayer());
                        
                        if (playerLogin.isLoggedIn()) { //判断这个b是否登陆
                            ctx.getSource().sendFeedback(Text.literal("§c您已登录! 请不要重复登录!"), false);
                        }
                        else if (!RegisteredPlayersJson.isPlayerRegistered(username)) {//判断这个b是否注册
                            ctx.getSource().sendFeedback(Text.literal("§c你还未在本服务器注册,请使用 /reg 进行注册。"), false);
                        } else if (RegisteredPlayersJson.isCorrectPassword(username, password)) {//登陆成功后做什么
                            playerLogin.setLoggedIn(true);
                            ctx.getSource().sendFeedback(Text.literal("§a登录成功。"), false);
                            if (!player.isCreative()) {
                                player.setInvulnerable(false);
                            }
                        } else {//不满足以上条件判定为密码错误
                            ctx.getSource().sendFeedback(Text.literal("§c登录失败。请检查密码是否正确,如忘记密码,请联系服务器管理员。"), false);
                        }
                        return 1;
        })));
    }
}
