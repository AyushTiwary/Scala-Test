package services
import module.Inventory

class itemServices {

  def sortByPrice(inventory: Inventory, filterBy : String) = {

    val filteredList = inventory.items.sortBy(_.price.cost)
    if(filterBy.equalsIgnoreCase("LowTOHigh")) {
      Inventory(filteredList)
    }
    else Inventory(filteredList.reverse)
  }

  def findItem(inventory: Inventory, itemType: String, itemName: String, filter : Option[String]) = {

    val searchList = inventory.items.filter(_.itemType==itemType).filter(_.name==itemName)
    if(searchList.isEmpty) throw new NoSuchElementException
    else {
      filter match {
        case Some(elem) => sortByPrice(Inventory(searchList),elem)
        case None => searchList

      }
    }
  }



}
