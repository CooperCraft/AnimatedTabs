package com.RenoVirus.AnimatedTabs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.RenoVirus.AnimatedTabs.Commands.MainCmdCommandExecutor;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class AnimatedTabs extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getLogger().info("AnimatedTabs has been enabled!");{}
		getConfig().options().copyDefaults(true);
		saveConfig();
		setupCmds();
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("AnimatedTabs has been disabled!");{
			
		}
			
		}
		
		
	private void setupCmds(){
		getCommand("AnimatedTabs").setExecutor(new MainCmdCommandExecutor());
	}
	
	public static void setPlayerListHeader(Player player,String header,String footer){
        CraftPlayer cplayer = (CraftPlayer) player;
        PlayerConnection connection = cplayer.getHandle().playerConnection;
        IChatBaseComponent hj = ChatSerializer.a("{'text':'"+header+"'}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try{
            java.lang.reflect.Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, hj);
            headerField.setAccessible(!headerField.isAccessible());
         
        } catch (Exception e){
            e.printStackTrace();
        }
        connection.sendPacket(packet);
    }
 
 
    public static void setPlayerListFooter(Player player,String footer){
        CraftPlayer cp = (CraftPlayer) player;
        PlayerConnection con = cp.getHandle().playerConnection;
        IChatBaseComponent fj = ChatSerializer.a("{'text':'"+footer+"'}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try{
            java.lang.reflect.Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, fj);
            footerField.setAccessible(!footerField.isAccessible());
        }catch(Exception e){
            e.printStackTrace();
        }
        con.sendPacket(packet);
    }
	public boolean onCommand1(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("TabHeader")){
			player.sendMessage(getConfig().getString("TabHeader"));
		}
		return false;
		}}


