package org.msa.item.cotroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msa.item.domain.Item;
import org.msa.item.dto.ItemDTO;
import org.msa.item.dto.ResponseDTO;
import org.msa.item.dto.constant.ItemType;
import org.msa.item.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "v1/item")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;
	
	@RequestMapping(value="/add/{itemType}", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> add(@Valid @RequestBody ItemDTO itemDTO, @PathVariable String itemType) {
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();

		log.debug("path.variable itemType = {}", itemType);
		boolean hasItemType = false;
		ItemType [] itemTypeList = ItemType.values();
		for(ItemType i : itemTypeList) {
			if (i.hasItemCd(itemType)) {
				hasItemType = true;
				break; // ItemType을 찾았으면 루프를 종료합니다.
			}
		}

		if(!hasItemType) {
			responseBuilder.code("500").message("invalid itemType .[" + itemType + "]");
			return ResponseEntity.ok(responseBuilder.build());
		}else {
			itemDTO.setItemType(itemType);
		}

		itemDTO.setItemType(itemType);
		itemService.insertItem(itemDTO);
		log.debug("requset add item id = {}", itemDTO.getId());
		
		responseBuilder.code("200").message("success");
		return ResponseEntity.ok(responseBuilder.build());
	}

}
