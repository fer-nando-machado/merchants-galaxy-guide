package br.com.fernandomachado.galaxy.dao.singleton;

import br.com.fernandomachado.galaxy.dao.MaterialDAO;
import br.com.fernandomachado.galaxy.model.Currency;
import br.com.fernandomachado.galaxy.model.Material;
import br.com.fernandomachado.galaxy.model.Price;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link MaterialDAO}.
 * Singleton class storing {@link Material} data on a {@link Map}.
 */
public class MaterialSingletonDAO implements MaterialDAO {

    private static MaterialSingletonDAO instance;

    private Map<String, Map<String, Double>> map;

    MaterialSingletonDAO() {
        this.map = new HashMap<>();
    }

    static MaterialSingletonDAO getInstance() {
        if (instance == null) {
            instance = new MaterialSingletonDAO();
        }
        return instance;
    }

    @Override
    public void save(Material material) {
        if (!this.map.containsKey(material.getName())) {
            this.map.put(material.getName(), new HashMap<>());
        }
        Map<String, Double> prices = this.map.get(material.getName());
        for (Price price : material.getPrices()) {
            prices.put(price.getCurrency().getName(), price.getUnits());
        }
    }

    @Override
    public Material find(String name) {
        if (!this.map.containsKey(name)) {
            return null;
        }
        Material material = new Material(name);
        Map<String, Double> priceMap = this.map.get(name);
        for (String currency : priceMap.keySet()) {
            material.addPrice(new Price(new Currency(currency), priceMap.get(currency)));
        }
        return material;
    }

    @Override
    public Double findPrice(String name, String currency) {
        if (!this.map.containsKey(name)) {
            return null;
        }
        return this.map.get(name).get(currency);
    }

}
