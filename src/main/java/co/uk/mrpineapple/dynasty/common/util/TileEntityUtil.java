package co.uk.mrpineapple.dynasty.common.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.stream.Stream;

public class TileEntityUtil {
    public static void sendUpdatePacket(TileEntity tileEntity, CompoundNBT compound) {
        SUpdateTileEntityPacket packet = new SUpdateTileEntityPacket(tileEntity.getBlockPos(), 0, compound);
        sendUpdatePacket(tileEntity.getLevel(), tileEntity.getBlockPos(), packet);
    }

    private static void sendUpdatePacket(World world, BlockPos pos, SUpdateTileEntityPacket packet) {
        if(world instanceof ServerWorld) {
            ServerWorld server = (ServerWorld) world;
            Stream<ServerPlayerEntity> players = server.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false);
            players.forEach(player -> player.connection.send(packet));
        }
    }
}
