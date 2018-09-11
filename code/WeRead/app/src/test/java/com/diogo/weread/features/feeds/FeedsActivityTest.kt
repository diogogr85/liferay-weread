package com.liferay.diogo.features.feeds

import com.diogo.weread.features.feeds.FeedsActivity
import com.diogo.weread.features.feeds.FeedsPresenter
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FeedsActivityTest {

    private lateinit var feedActivity: FeedsActivity

    @Mock
    private lateinit var presenter: FeedsPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = FeedsPresenter()
    }

}