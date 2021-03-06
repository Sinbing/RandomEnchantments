package com.tfar.randomenchants.ench.enchantment;

import com.tfar.randomenchants.Config;
import com.tfar.randomenchants.RandomEnchants;
import com.tfar.randomenchants.util.EnchantUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.tfar.randomenchants.Config.Restriction.*;
import static com.tfar.randomenchants.RandomEnchants.ObjectHolders.OBSIDIAN_BUSTER;

@Mod.EventBusSubscriber(modid= RandomEnchants.MODID)
public class EnchantmentObsidianBuster extends Enchantment {
  public EnchantmentObsidianBuster() {

    super(Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{
            EquipmentSlotType.MAINHAND
    });
    this.setRegistryName("obsidian_buster");
  }

  @Override
  public int getMinEnchantability(int level) {
    return 15;
  }

  @Override
  public int getMaxLevel() {
    return 1;
  }

  @Override
  public boolean canApply(ItemStack stack){
    return Config.ServerConfig.obsidian_buster.get() != DISABLED && super.canApply(stack);
  }

  @Override
  public boolean isTreasureEnchantment() {
    return Config.ServerConfig.obsidian_buster.get() == ANVIL;
  }

  @Override
  public boolean canApplyAtEnchantingTable(ItemStack stack) {
    return Config.ServerConfig.obsidian_buster.get() != DISABLED && super.canApplyAtEnchantingTable(stack);
  }

  @Override
  public boolean isAllowedOnBooks() {
    return Config.ServerConfig.obsidian_buster.get() == NORMAL;
  }

  @SubscribeEvent
  public static void onBreakSpeed(PlayerEvent.BreakSpeed e) {
    PlayerEntity p = e.getEntityPlayer();
    BlockState state = e.getState();
    if (EnchantUtils.hasEnch(p, OBSIDIAN_BUSTER) && state.getBlock() == Blocks.OBSIDIAN) {
      float oldSpeed = e.getOriginalSpeed();
      e.setNewSpeed(oldSpeed + 100F);
    }
  }
}

