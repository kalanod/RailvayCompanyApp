package com.sizran.railvaycompany.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sizran.railvaycompany.R
import com.sizran.railvaycompany.adapters.Station
import com.sizran.railvaycompany.adapters.StationsAdapter

class FragmentStations : Fragment() {

    private lateinit var adapter: StationsAdapter
    private lateinit var stationList: List<Station>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stations, container, false)

        // Initialize the station list (this should come from your data source)
        stationList = listOf(
            Station(
                name = "Пристань Тихой Реки",
                city = "Сызрань",
                description = "Станция расположена на берегу Тихой Реки, окруженной живописными лесами и полями, и служит ключевым узлом для перевозки грузов и пассажиров из сельских районов в город."
            ),
            Station(
                name = "Солнечная поляна",
                city = "Сызрань",
                description = "Станция Солнечная поляна известна своими просторными полями, на которых во все времена года цветут яркие цветы. Здесь можно увидеть волшебный закат, окрашивающий небо в красные и оранжевые оттенки."
            ),
            Station(
                name = "Вишневая Роща",
                city = "Сызрань",
                description = "Станция Вишневая Роща находится среди пышной зелени фруктовых садов. Весной здесь цветут яркие вишневые деревья, привлекая туристов своей красотой и ароматом."
            ),
            Station(
                name = "Золотые Поляны",
                city = "Сызрань",
                description = "Станция Золотые Поляны расположена в районе богатых золотых рудников, привлекающих искателей приключений и золота со всего региона."
            ),
            Station(
                name = "Лунная Пустошь",
                city = "Сызрань",
                description = "Станция Лунная Пустошь окружена необычными скальными образованиями, напоминающими лунный пейзаж. Это место служит источником вдохновения для художников и поэтов."
            ),
            Station(
                name = "Старый Мельничный Двор",
                city = "Сызрань",
                description = "Станция расположена рядом с старинным мельничным двором, где работают старинные мельницы, привлекающие туристов своим историческим значением и атмосферой."
            ),
            Station(
                name = "Заповедные леса",
                city = "Сызрань",
                description = "Станция Заповедные леса находится в самом сердце заповедной зоны, где можно встретить редких видов дикой природы и насладиться уединением и тишиной."
            ),
            Station(
                name = "Полярные Сияния",
                city = "Сызрань",
                description = "Станция Полярные Сияния славится своими ночными видами северного сияния, которые можно увидеть в холодные зимние ночи, создавая впечатление магической атмосферы."
            ),
            Station(
                name = "Дремучий Лес",
                city = "Сызрань",
                description = "Станция Дремучий Лес находится в глубине густого леса, полного тайн и загадок, привлекающего путешественников своей загадочной атмосферой."
            ),
            Station(
                name = "Звездный Путь",
                city = "Сызрань",
                description = "Станция Звездный Путь расположена под открытым небом, где можно наблюдать за звездным небом и планетами, создавая впечатление путешествия по космосу."
            ),
            Station(
                name = "Золотая долина",
                city = "Красноармейск",
                description = "Станция Золотая долина находится в городе Красноармейск, окруженном живописными долинами и озерами, и является ключевым узлом для транспортировки сельскохозяйственной продукции."
            ),
            Station(
                name = "Вершина Гор",
                city = "Тольятти",
                description = "Станция Вершина Гор расположена в городе Тольятти, у подножия высоких гор, и служит ключевым пунктом транзитной торговли между городами и селами региона."
            ),
            Station(
                name = "Поднебесные Выси",
                city = "Ульяновск",
                description = "Станция Поднебесные Выси находится в городе Ульяновск, в окружении высоких гор и облаков, и является важным центром туризма и рекреации для местных жителей и туристов."
            ),
            Station(
                name = "Солнечные Луга",
                city = "Димитровград",
                description = "Станция Солнечные Луга находится в городе Димитровград, окруженном просторными лугами и полями, и служит ключевым узлом для транспортировки сельскохозяйственной продукции."
            ),
            Station(
                name = "Горное Поднебесье",
                city = "Новокуйбышевск",
                description = "Станция Горное Поднебесье находится в городе Новокуйбышевск, среди высоких гор и красивых лугов, и служит популярным местом отдыха и туризма для местных жителей и туристов."
            ),
            Station(
                name = "Полярная Звезда",
                city = "Чапаевск",
                description = "Станция Полярная Звезда находится в городе Чапаевск, где можно наблюдать за уникальными северными явлениями, создавая впечатление волшебного путешествия."
            ),
            Station(
                name = "Речной Берег",
                city = "Кинель",
                description = "Станция Речной Берег находится в городе Кинель, у берега мощной реки, и служит ключевым узлом для перевозки грузов и пассажиров в этом районе."
            ),
            Station(
                name = "Зеленый Луг",
                city = "Жигулевск",
                description = "Станция Зеленый Луг находится в городе Жигулевск, окруженном просторными лугами и полями, и служит важным пунктом для транспортировки сельскохозяйственной продукции."
            ),
            Station(
                name = "Славянские Высоты",
                city = "Самара",
                description = "Станция Славянские Высоты находится в городе Самара, на высоких горных вершинах, и является популярным местом для туризма и экскурсий для местных жителей и гостей города."
            ),
            Station(
                name = "Прибрежные Красоты",
                city = "Нефтегорск",
                description = "Станция Прибрежные Красоты находится в городе Нефтегорск, на берегу живописных рек и озер, и служит ключевым пунктом для туризма и отдыха для местных жителей и туристов."
            )
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_stations)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = StationsAdapter(requireContext(), stationList)
        recyclerView.adapter = adapter

        val searchBar: EditText = view.findViewById(R.id.search_bar)
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }
        })

        return view
    }
}