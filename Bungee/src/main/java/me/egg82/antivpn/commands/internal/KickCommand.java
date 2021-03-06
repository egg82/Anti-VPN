package me.egg82.antivpn.commands.internal;

import co.aikar.commands.CommandIssuer;
import me.egg82.antivpn.api.VPNAPIProvider;
import me.egg82.antivpn.api.model.ip.IPManager;
import me.egg82.antivpn.api.model.player.PlayerManager;
import me.egg82.antivpn.config.CachedConfig;
import me.egg82.antivpn.config.ConfigUtil;
import me.egg82.antivpn.locale.MessageKey;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

public class KickCommand extends AbstractCommand {
    private final String player;
    private final String type;

    public KickCommand(@NotNull CommandIssuer issuer, @NotNull String player, @NotNull String type) {
        super(issuer);
        this.player = player;
        this.type = type;
    }

    @Override
    public void run() {
        CachedConfig cachedConfig = ConfigUtil.getCachedConfig();

        ProxiedPlayer p = ProxyServer.getInstance().getPlayer(player);
        if (p == null) {
            issuer.sendError(MessageKey.KICK__NO_PLAYER);
            return;
        }

        String ip = getIp(p.getAddress());
        if (ip == null) {
            logger.error("Could not get IP for player " + p.getName());
            issuer.sendError(MessageKey.ERROR__INTERNAL);
            return;
        }

        if (type.equalsIgnoreCase("vpn")) {
            IPManager ipManager = VPNAPIProvider.getInstance().getIPManager();

            if (cachedConfig.getVPNActionCommands().isEmpty() && cachedConfig.getVPNKickMessage().isEmpty()) {
                issuer.sendError(MessageKey.KICK__API_MODE);
                return;
            }
            List<String> commands = ipManager.getVpnCommands(p.getName(), p.getUniqueId(), ip);
            for (String command : commands) {
                ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), command);
            }
            String kickMessage = ipManager.getVpnKickMessage(p.getName(), p.getUniqueId(), ip);
            if (kickMessage != null) {
                p.disconnect(TextComponent.fromLegacyText(kickMessage));
            }

            issuer.sendInfo(MessageKey.KICK__END_VPN, "{player}", player);
        } else if (type.equalsIgnoreCase("mcleaks")) {
            PlayerManager playerManager = VPNAPIProvider.getInstance().getPlayerManager();

            if (cachedConfig.getMCLeaksActionCommands().isEmpty() && cachedConfig.getMCLeaksKickMessage().isEmpty()) {
                issuer.sendError(MessageKey.KICK__API_MODE);
                return;
            }
            List<String> commands = playerManager.getMcLeaksCommands(p.getName(), p.getUniqueId(), ip);
            for (String command : commands) {
                ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), command);
            }
            String kickMessage = playerManager.getMcLeaksKickMessage(p.getName(), p.getUniqueId(), ip);
            if (kickMessage != null) {
                p.disconnect(TextComponent.fromLegacyText(kickMessage));
            }

            issuer.sendInfo(MessageKey.KICK__END_MCLEAKS, "{player}", player);
        }
    }

    private @Nullable String getIp(InetSocketAddress address) {
        if (address == null) {
            return null;
        }
        InetAddress host = address.getAddress();
        if (host == null) {
            return null;
        }
        return host.getHostAddress();
    }
}
