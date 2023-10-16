package net.mukundshyam.afkhealth.item.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class ActivatorItem extends Item {
    public ActivatorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockState blockState = context.getWorld().getBlockState(context.getBlockPos());
            PlayerEntity player = context.getPlayer();
            if (blockState.getBlock() instanceof ChestBlock || blockState.getBlock() instanceof BarrelBlock || blockState.getBlock() instanceof EnderChestBlock) {
                assert player != null;
                outputYes(player);
            }
            else  {
                assert player != null;
                outputNo(player);
            }
        }
        return ActionResult.SUCCESS;
    }

    private void outputYes(PlayerEntity player) {
        player.sendMessage(Text.literal("Is Chest"), true);
    }
    private void outputNo(PlayerEntity player) {
        player.sendMessage(Text.literal("Not Chest"), true);
    }
}
