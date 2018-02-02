package cabanas.garcia.ismael.dddinpractice.management.domain.model;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.BalanceChargedEvent;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.Handler;

import java.util.Optional;

public class BalanceChargedHandler implements Handler<BalanceChargedEvent> {

    private final HeadOfficeRepository headOfficeRepository;

    public BalanceChargedHandler(HeadOfficeRepository headOfficeRepository) {
        this.headOfficeRepository = headOfficeRepository;
    }

    @Override
    public void handle(BalanceChargedEvent balanceChargedEvent) {
        Optional<HeadOffice> headOffice = this.headOfficeRepository.findById(new HeadOfficeId("1"));

        headOffice.ifPresent(ho -> {
            ho.changeBalance(balanceChargedEvent.getAmountCharged());
            this.headOfficeRepository.save(ho);
        });
    }
}
