package org.vmlurui.remclogin;

import org.vmlurui.remclogin.PlayerLogin;
import net.minecraft.server.network.ServerPlayerEntity;
import java.util.HashMap;
import java.util.UUID;

public class GetPlayer extends HashMap<UUID, PlayerLogin> {
    public PlayerLogin get(ServerPlayerEntity player) {
        UUID playerUUID = player.getUuid();
        if (containsKey(playerUUID)) {
            return super.get(playerUUID);
        }
        PlayerLogin newPlayer = new PlayerLogin(player);
        put(playerUUID, newPlayer);
        return newPlayer;
    }
}
