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
import net.minecraft.text.Text;
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
                        
                        if (playerLogin.isLoggedIn()) { //判断这个b是否登陆
                            ctx.getSource().sendFeedback(Text.translatable("command.login.l_already"), false);
                        }
                        else if (!RegisteredPlayersJson.isPlayerRegistered(username)) {//判断这个b是否注册
                            ctx.getSource().sendFeedback(Text.translatable("command._user_not_found"), false);
                        } else if (RegisteredPlayersJson.isCorrectPassword(username, password)) {//登陆成功后做什么
                            playerLogin.setLoggedIn(true);
                            ctx.getSource().sendFeedback(Text.translatable("command.login.l_success"), false);
                            if (!player.isCreative()) {
                                player.setInvulnerable(false);
                            }
                            player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(new Identifier("minecraft:block.note_block.pling"), SoundCategory.MASTER, player.getPos(), 100f, 0f));//放一个音符盒音效
                        } else {//不满足以上条件判定为密码错误
                            player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(new Identifier("minecraft:entity.zombie.attack_iron_door"), SoundCategory.MASTER, player.getPos(), 100f, 0.5f));//放一个僵尸砸门的音效
                            ctx.getSource().sendFeedback(Text.translatable("command.login.l_password_incorrect"), false);
                        }
                        return 1;
        })));
    }
}
