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

public class PasswordCommand {
  public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
    dispatcher.register(literal("passwd")
      .then(argument("oldPassword", StringArgumentType.word())
      .then(argument("newPassword", StringArgumentType.word())
      .then(argument("confirmPassword", StringArgumentType.word())
           .executes(ctx -> {
              String oldPasswd = StringArgumentType.getString(ctx, "oldPassword");
              String newPasswd = StringArgumentType.getString(ctx, "newPassword");
              String confirmPassword = StringArgumentType.getString(ctx, "confirmPassword");
              String username = ctx.getSource().getPlayer().getEntityName();
              ServerPlayerEntity player = ctx.getSource().getPlayer();

              if (RegisteredPlayersJson.isCorrectPassword(username, oldPasswd)) {//判断密码是否正确
                if (!newPasswd.equals(confirmPassword)) {//判断新密码与确认密码不一致则
                    ctx.getSource().sendFeedback(Text.translatable("command.password.passwd_new_no_match"), false);
                } else if(newPasswd.equals(oldPasswd)) {//判断新密码与旧密码一致则
                  player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(new Identifier("minecraft:entity.zombie.attack_iron_door"), SoundCategory.MASTER, player.getPos(), 100f, 0.5f));
                  ctx.getSource().sendFeedback(Text.translatable("command.password.passwd_new_same"), false);
                  } else {//不满足上述条件 判断允许修改
                    String uuid = ctx.getSource().getPlayer().getUuidAsString();
                    RegisteredPlayersJson.removePlayer(username);
                    RegisteredPlayersJson.save(uuid, username, newPasswd);
                    PlayerLogin playerLogin = LoginMod.getPlayer(ctx.getSource().getPlayer());
                    player.setInvulnerable(false);
                    ctx.getSource().sendFeedback(Text.translatable("command.password.passwd_success"), false);
                    return 1;
                 }
                 return 1;
                } else {//34行if的else 判断密码与服务器上的不一致
                player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(new Identifier("minecraft:entity.zombie.attack_iron_door"), SoundCategory.MASTER, player.getPos(), 100f, 0.5f));
                ctx.getSource().sendFeedback(Text.translatable("command.password.passwd_old_incorrect"), false);
              }
              return 1;
            }
    )))));
  }
}
