package org.vmlurui.remclogin.commands;

import org.molaei.mclogin.LoginMod;
import org.molaei.mclogin.PlayerLogin;
import org.molaei.mclogin.RegisteredPlayersJson;
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

public class PasswordCommand {
  public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
    dispatcher.register(literal("password")
      .then(argument("oldPassword", StringArgumentType.word())
      .then(argument("newPassword", StringArgumentType.word())
      .then(argument("confirmPassword", StringArgumentType.word())
           .executes(ctx -> {
              String oldPasswd = StringArgumentType.getString(ctx, "oldPassword");
              String newPasswd = StringArgumentType.getString(ctx, "newPassword");
              String confirmPassword = StringArgumentType.getString(ctx, "confirmPassword");
              String username = ctx.getSource().getPlayer().getEntityName();
              ServerPlayerEntity player = ctx.getSource().getPlayer();

              if (!RegisteredPlayersJson.isPlayerRegistered(username)) {
                 ctx.getSource().sendFeedback(new LiteralText("§cYou're not registered! Use /register instead."), false);
                 ctx.getSource().sendFeedback(new LiteralText("§c你还未在本服务器注册，请使用 /register 进行注册。"), false);
              } else if (RegisteredPlayersJson.isCorrectPassword(username, oldPasswd)) {
                if (!newPasswd.equals(confirmPassword)) {
                    ctx.getSource().sendFeedback(new LiteralText("§cThe new password does not match the confirmation password! Repeat it correctly."), false);
                    ctx.getSource().sendFeedback(new LiteralText("§c新密码与确认密码不一致!请重试。"), false);
                } else if(newPasswd.equals(oldPasswd)) {
                  player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(new Identifier("minecraft:entity.zombie.attack_iron_door"), SoundCategory.MASTER, player.getPos(), 100f, 0.5f));
                  ctx.getSource().sendFeedback(new LiteralText("§cThe new password cannot be the same as the old one!"), false);
                  ctx.getSource().sendFeedback(new LiteralText("§c新密码不能与旧密码相同!"), false);
                  } else {
                    String uuid = ctx.getSource().getPlayer().getUuidAsString();
                    RegisteredPlayersJson.removeUsr(username);
                    RegisteredPlayersJson.save(uuid, username, newPasswd);
                    PlayerLogin playerLogin = LoginMod.getPlayer(ctx.getSource().getPlayer());
                    player.setInvulnerable(false);
                    ctx.getSource().sendFeedback(new LiteralText("§aPassword changed successfully."), false);
                    ctx.getSource().sendFeedback(new LiteralText("§a密码修改成功!"), false);
                    return 1;
                 }
                 return 1;
                } else {
                player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(new Identifier("minecraft:entity.zombie.attack_iron_door"), SoundCategory.MASTER, player.getPos(), 100f, 0.5f));
                ctx.getSource().sendFeedback(new LiteralText("§cIncorrect old password!"), false);
                ctx.getSource().sendFeedback(new LiteralText("§c旧密码错误!"), false);
              }
              return 1;
            }
    )))));
  }
}
