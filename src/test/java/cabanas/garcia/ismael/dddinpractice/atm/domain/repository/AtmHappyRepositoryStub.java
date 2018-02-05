package cabanas.garcia.ismael.dddinpractice.atm.domain.repository;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class AtmHappyRepositoryStub implements AtmRepository {
    private final Atm atm;
    private final AtmRepository atmRepositoryMock;

    public AtmHappyRepositoryStub(Atm atm, AtmRepository atmRepositoryMock) {
        this.atm = atm;
        this.atmRepositoryMock = atmRepositoryMock;
    }

    @Override
    public void save(Atm atmToSave) {
        atmRepositoryMock.save(atmToSave);
    }

    @Override
    public Optional<Atm> findById(AtmId atmId) {
        return Optional.of(atm);
    }

    public void verifySaveAtmWithMoney(Money money) {
        ArgumentCaptor<Atm> atmArgCaptor = ArgumentCaptor.forClass(Atm.class);
        verify(atmRepositoryMock, times(1)).save(atmArgCaptor.capture());
        assertThat(atmArgCaptor.getValue().moneyInside()).isEqualTo(money);
    }

    public void verifySaveAtmWithMoney(Money money, double amountCharged) {
        ArgumentCaptor<Atm> atmArgCaptor = ArgumentCaptor.forClass(Atm.class);
        verify(atmRepositoryMock, times(1)).save(atmArgCaptor.capture());
        assertThat(atmArgCaptor.getValue().moneyInside()).isEqualTo(money);
        assertThat(atmArgCaptor.getValue().moneyCharged()).isEqualTo(amountCharged);
    }
}
