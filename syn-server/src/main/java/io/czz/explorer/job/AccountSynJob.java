package io.czz.explorer.job;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.czz.explorer.SynServerConfig;
import io.czz.explorer.exception.ServiceException;
import io.czz.explorer.service.AccountService;
import io.czz.explorer.syn.SynAccount;
import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@DisallowConcurrentExecution
public class AccountSynJob {

	private AccountService accountService;
	private SynAccount synAccount;
	private SynServerConfig config;
	private static final Logger logger = LoggerFactory.getLogger(AccountSynJob.class);
	@Inject
	public AccountSynJob( AccountService accountService, SynServerConfig config, SynAccount synAccount) {
		this.accountService = accountService;
		this.synAccount = synAccount;
		this.config = config;
	}

	@Scheduled("50ms")
	public void syncAccount() throws ServiceException {

		if (!this.config.isAccountJobEnabled()) {
			return;
		}

		this.synAccount.syncAccounts();

	}
//
//	@Scheduled("20ms")
//	public void syncAccountVote() throws ServiceException {
//
//		if (!this.config.isAccountJobEnabled()) {
//			return;
//		}
//
//		this.accountSyncService.syncAccountVote();
//
//	}

	@Scheduled("100ms")
	public void syncAccountResync() throws ServiceException {

		if (!this.config.isAccountJobEnabled()) {
			return;
		}

		this.synAccount.syncAccountResync();

	}


    @Scheduled("1000ms")
    public void checkUtxo() throws ServiceException {

        this.synAccount.syncAccountResync();

    }

//	@Scheduled("5m")
//	public void removeLocks() {
//		this.accountSyncService.removeLocks();
//	}

	public void syncGenesisAccounts() {
		//TODO:
		//Genesis accounts might be used without any transactions appearing on blockchain : for example block rewarding
		// These accounts are updated here

	}
	
}
