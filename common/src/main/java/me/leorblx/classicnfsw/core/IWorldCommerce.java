package me.leorblx.classicnfsw.core;

public interface IWorldCommerce
{
    /**
     * Save commerce data to XML files.
     */
    String saveCommerceData(Long personaId, String commerceData);

    /**
     * Map a product ID to its category.
     */
    String mapProductId(String productId);
}
