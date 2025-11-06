# Thaumcraft NEI Plugin : Patched
## Addon for Thaumcraft NEI Plugin, made by Thenemex
### Patched bugs :
- Infusion, Crucible and both Shaped & Shapeless Arcane used to have issues not showing all recipes in the original mod. This was caused by the mod comparing the stackSize from the item clicked and all recipes.
    - For exemple, Crucible recipe for Thauminite [Thaumic Bases] yields 8 nuggets. If you clicked the Thauminite nugget in NotEnoughItems menu, it would show you no recipe, as the item you click in NEI only got a stackSize of 1, and the output of that recipe got a stackSize of 8.
