package net.mukundshyam.afkhealth.item.custom;

import com.sun.jna.platform.unix.solaris.Kstat2StatusException;
import net.minecraft.block.*;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.ObjectUtils;

public class ActivatorItem extends Item{

    public ActivatorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)  {
        if (!context.getWorld().isClient()){
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            BlockEntity type = context.getWorld().getBlockEntity(positionClicked);
            assert player != null;
            if (type instanceof ChestBlockEntity) {
                int total = 0;
                for (int num = 0; num <= 26; num++) {
                    int count = getChestStacks((ChestBlockEntity) type, num);
                    total = total + count;
                }
                String totalVal = String.valueOf(total);
                player.sendMessage(Text.literal(totalVal + " items") , true);
            }
            else if (type instanceof BarrelBlockEntity) {
                int total = 0;
                for (int num = 0; num <= 26; num++) {
                    int count = getBarrelStacks((BarrelBlockEntity) type, num);
                    total = total + count;
                }
                String totalVal = String.valueOf(total);
                player.sendMessage(Text.literal(totalVal + " items") , true);
            }
            else if (type instanceof EnderChestBlockEntity) {
                player.sendMessage(Text.literal("Doesn't work with ender chests."), true);
            }
            else {
                player.sendMessage(Text.literal("Doesn't work with this block."), true);
            }
        }
        return ActionResult.SUCCESS;
    }
    public int getChestStacks(ChestBlockEntity chest, int i){
        ItemStack itemStack = chest.getStack(i);
        return itemStack.getCount();
    }
    public int getBarrelStacks(BarrelBlockEntity barrel, int i){
        ItemStack itemStack = barrel.getStack(i);
        return itemStack.getCount();
    }
}
