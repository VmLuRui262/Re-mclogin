package org.vmlurui.remclogin;

import org.vmlurui.remclogin.commands.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.network.ServerPlayerEntity;

public class LoginMod implements ModInitializer {
    static GetPlayer getPlayer = new GetPlayer();

    @Override
    public void onInitialize() {
        RegisteredPlayersJson.read();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {//创建指令后一定要在此处注册，否则将无法触发
            LoginCommand.register(dispatcher);
            RegisterCommand.register(dispatcher);
            PasswordCommand.register(dispatcher);
            DeRegisterCommand.register(dispatcher);
        });
    }

    public static PlayerLogin getPlayer(ServerPlayerEntity player) {
        return getPlayer.get(player);
    }
}
